(ns users.delete
  (:require [cheshire.core :as json]
            [clojure.data.json :as json-clojure]
            [clojure.java.io :as io]
            [users.read :as read]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn delete-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        users (read/get-users)
        user (first (filter #(= (:id %) id) users))]
    (if user
      (let [updated-users (remove #(= (:id %) id) users)]
        (spit file-path (json-clojure/write-str updated-users :pretty true))
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body {:message (str "Usuário com o id " id " deletado com sucesso")}})
      {:status 404
       :headers {"Content-Type" "application/json"}
       :body {:error (str "Usuário com o id " id " não encontrado")}}
      )))
