(ns users.read
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn get-users []
  (if (.exists (io/file file-path))
  (json/read-str (slurp file-path) :key-fn  keyword)
  []))

(defn read-users [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (get-users)})

(defn read-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        users (get-users)
        user (first (filter #(= (str (:id %)) id) users))]
    (if user
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body user}
    {:status 404
     :headers {"Content-Type" "application/json"}
     :body {:error (str "Usuário com o id" id "não encontrado")}})))