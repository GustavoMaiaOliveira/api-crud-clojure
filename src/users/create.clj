(ns users.create
  (:require [cheshire.core :as json]
            [clojure.java.io :as io]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")

(defn create-new-user [request]
  (let [new-user (:json-params request)
        existing-users (if (.exists (io/file file-path))
                         (json/parse-string (slurp file-path) true)
                         [])
        updated-users (conj existing-users new-user)]
    (spit file-path (json/generate-string updated-users {:pretty true}))
    {:status 201
     :headers {"Content-Type" "application/json"}
     :body (json/generate-string {:message "Usuário criado com sucesso!"
                                  :user new-user})}))

pop assoc

;(ns users.create
;  (:require [cheshire.core :as json]
;            [clojure.java.io :as io]))
;
;(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")
;
;(defn create-users [request]
;  (let [new-user (:json-params request)
;    existing-users (if (.exists (io/file file-path))
;                     (json/parse-string (slurp file-path) true)
;                     [])
;    updated-users (conj existing-users new-user)]
;    (spit file-path
;          (str (json/generate-string new-user) "\n")
;          :append true)
;    {:status 201
;     :headers {"Content-Type" "application/json"}
;     :body (json/generate-string {:message "Usuário criado com sucesso!"
;                                  :user new-user})
;     ;:body request
;     }))
;
;(defn create-new-user [request]
;  (let [new-user (:json-params request)
;        existing-users (json/parse-string (slurp file-path) true)]
;  (conj existing-users new-user)))

;TODO
; - 1 criar endpoint post - DO
; - Receber dados - DO
; - entender como salvar esses dados em um json ja existente - DO
; - retornar info do user criado - DO
; - criar uuid