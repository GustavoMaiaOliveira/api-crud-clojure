(ns users.read
  (:require [clojure.data.json :as json])
  )

(defn get-users
  [path]
  (json/read-str (slurp path)))

(get-users "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn read-users
  [request]
  {:status 200
   :body (get-users "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")})