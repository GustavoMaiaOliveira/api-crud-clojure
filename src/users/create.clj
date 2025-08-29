(ns users.create
  (:require [cheshire.core :as json]))

;(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")
;
;(defn create-users [request]
;  (let [body (slurp (:body request))
;        new-user (json/parse-string body true)
;        existing-users (if (.exists (clojure.java.io/file file-path))
;                         (json/parse-string (slurp file-path) true)
;                         [])
;        updated-users (conj existing-users new-user)]
;    (spit file-path (json/generate-string updated-users {:pretty true}))
;    {:status 201
;     :headers {"Content-Type" "application/json"}
;     :body (json/generate-string {:message "Usuário criado com sucesso!"})}))

(defn create-users [request]
  (let [user (:json-params request)]
    (spit "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json" (json/generate-string user {:pretty true}))
    {:status 201
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:message "Usuário criado com sucesso!"
                                  :user user})}))

;TODO
; - 1 criar endpoint post - DO
; - Receber dados
; - entender como salvar esses dados em um json ja existente
; - retornar info do user criado - DO