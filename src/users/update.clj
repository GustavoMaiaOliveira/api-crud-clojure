(ns users.update
  (:require [clojure.data.json :as json]
            [helpers.file-path :as file-path]))


(defn get-all []
  (json/read-json (slurp "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users-backup.json")))

(defn get-user [id]
  (first (filter #(= (:id %) (read-string id)) (get-all))))

(defn update-users-by-id [request]
  (let [all-users (get-all)
        user (get-user (read-string(get-in request [:path-params :id])))
        user-updated (assoc user :age (read-string(:age (:json-params request))))
        without-old (remove #(= (:id %) (:id user)) all-users)
        updated-list (conj (vec without-old) user-updated)]

    (println updated-list)

    (spit "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json"
          (json/write-str updated-list))
    updated-list))


