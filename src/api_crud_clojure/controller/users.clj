(ns api-crud-clojure.controller.users)

(def db-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn get-users
  [request]
  {:status 200
   :body (slurp db-path)})