(ns users.read
  (:require [clojure.data.json :as json]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn get-users
  [path]
  (json/read-str (slurp path)))

(defn read-users
  [request]
  {:status 200
   :body (get-users file-path)})

