(ns users.update
  (:require [clojure.data.json :as json]
            [users.read :as read]
            [helpers.file-path :as file-path]))

(defn update-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        users (read/get-users)
        user (first (filter #(= (str (:id %)) id) users))]
    (if user
      (let [updated-users (remove #(= (str (:id %)) id) users)]
        (spit file-path/file-path (json/write-str updated-users :pretty true))
        {:status 200
         :headers {"Content-Type" "application/json"}
         :body {:message (str "Usuário com o id " id " deletado com sucesso")}})
      {:status 404
       :headers {"Content-Type" "application/json"}
       :body {:error (str "Usuário com o id " id " não encontrado")}}
      )))