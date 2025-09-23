(ns users.update
  (:require [clojure.data.json :as json]
            [helpers.file-path :as file-path]))

(defn get-data-by-id [id]
  (first (filter #(= (num (:id %)) id)
                 (json/read-json (slurp "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users-backup.json")))))

(defn update-users-by-id
  [request]
  (let [id (get-in request [:path-params :id])
        body (:json-params request)
        user (get-data-by-id (read-string id))
        new-age (:age body)
        id-user (:age (get-data-by-id (read-string id)))]

    (contains? user :id)
    (->
      (println "final test with dissoc")
      (println (dissoc user :age))
      (println "final test with assoc")
      (println (assoc user :age new-age))
      (json/write-str file-path/file-path))

    :else (print "Falha ao passar pelo teste")

    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    {:message (str "Retorno: " (:age user))}}))