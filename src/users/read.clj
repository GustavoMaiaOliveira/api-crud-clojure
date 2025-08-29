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

;(defn get-users
;  [request]
;  (println request)
;  {:status 200
;   :headers {"Content-Type" "application/json"}
;   :body (get-users-in-off["/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json"])})

;(defn read-users
;  [file-path]
;  {:status 200
;   :headers {"Context-Type" "text/plain"}
;   :body (get-users[file-path])})
;
;(read-users "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

;(defn funcao-hello
;  [request]
;  (print request)
;  {:status 200
;   :headers {"Content-Type" "text/plain"}
;   :body "Hello World"})

;(defn get-users
;  [request]
;  (println request)
;  {:status 200
;   :headers {"Content-Type" "application/json"}
;   :body {:username "Gustavo Maia"
;          :email "gustavo.maia@mmti.io"
;          :country "Brazil"
;          :city "SJP - PR"}})