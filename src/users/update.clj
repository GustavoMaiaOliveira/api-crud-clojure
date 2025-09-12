(ns users.update
  (:require [cheshire.core :as json]
            [clojure.data.json :as jsonclojure]
            [clojure.java.io :as io]
            [users.read :as read]))


(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn update-user [request]
  (let [id (get-in request [:path-params :id])
        new-data (:json-params request)
        existing-users (if (.exists (io/file file-path))
                         (json/parse-string (slurp file-path) true)
                         [])
        id-num (try
                 (Integer/parseInt id)
                 (catch Exception _ id))
        updated-users (mapv (fn [u]
                              (if (= (:id u) id-num)
                                (merge u new-data)
                                u))
                            existing-users)
        user-updated (first (filter #(= (:id %) id-num) updated-users))]

    (if user-updated
      (do
        (spit file-path (json/generate-string updated-users {:pretty true}))
        {:status 200
         :headers {"Content-Type" "application/json"}
         :body (json/generate-string {:message "Usuário atualizado com sucesso!"
                                      :user user-updated})})
      {:status 404
       :headers {"Content-Type" "application/json"}
       :body (json/generate-string {:error (str "Usuário com id " id " não encontrado")})})))