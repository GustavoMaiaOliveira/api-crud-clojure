(ns users.delete
  (:require [cheshire.core :as json]
            [clojure.data.json :as jsonclojure]
            [clojure.java.io :as io]
            [users.read :as read]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn delete-all-users [request]
  (let [params (:json-params request)
        nome (:nome params)
        existing-users (if (.exists (io/file file-path))
                         (json/parse-string (slurp file-path) true)
                          [])
        updated-users (remove #(= (:nome %) nome) existing-users)]
    (spit file-path (json/generate-string updated-users {:pretty true}))
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:message (str "Usuario" nome "deletado com sucesso!")})}))

(defn delete-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        users (read/get-users)
        user (first (filter #(= (str (:id %)) id) users))]
    (if user
      (let [updated-users (remove #(= (str (:id %)) id) users)]
        (spit file-path (jsonclojure/write-str updated-users :pretty true))
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body {:message (str "Usuário com o id " id " deletado com sucesso")}})
      {:status 404
       :headers {"Content-Type" "application/json"}
       :body {:error (str "Usuário com o id " id " não encontrado")}}
      )))