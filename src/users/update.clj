(ns users.update
  (:require [clojure.data.json :as json]
            [api-crud-clojure.helpers.file-path :as file-path]
            [users.read :as read]))

(defn update-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        users (read/get-users)
        user (first (filter #(= (str (:id %) id)) users))]
    (if user
      (let [new-data (:json-params request)
            old-data (user)]

        {:status 200
         :headers {"Content-Type" "application/json"}
         :body {:message (str new-data old-data user)}})
      {:status 404
       :headers {"Content-Type" "application/json"}
       :body {:error (str "nao funfo")}})))