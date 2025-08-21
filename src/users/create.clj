(ns users.create
  (:require [clojure.data.json :as json]))

(def users-json-string (slurp "/home/gustavo_maia/IdeaProjects/api-crud-clojure/src/users/users.json"))
(def users-data (json/read-str users-json-string :key-fn keyword))
(def new-user {:id 4 :name "Schmeller" :email "schmeller@mm.com"})
(def updated-users-data (conj users-data new-user))
(def update-users-json-string (json/write-str updated-users-data))

(spit "/home/gustavo_maia/IdeaProjects/api-crud-clojure/src/users/users.json" update-users-json-string)
