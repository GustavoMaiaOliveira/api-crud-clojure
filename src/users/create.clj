(ns users.create
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def file-path "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users.json")
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

;(defn create-users [request]
;  (let [user (:json-params request)]
;    (spit file-path (json/write-str user {:pretty true}))
;    {:status  201
;     :headers {"Content-Type" "application/json"}
;     :body    (json/write-str {:message "Usuário criado com sucesso!"
;                               :user    user})}))


;TODO
; - 1 criar endpoint post - DO
; - Receber dados
; - entender como salvar esses dados em um json ja existente
; - retornar info do user criado - DO



(defn add-user
  [new-user]
  (let [data (if (.exists (io/file file-path))
               (json/read-str (slurp file-path) :key-fn keyword)
               {:users []})
        updated (update data :users conj new-user)]
    (spit file-path (json/write-str updated {:pretty true}))
    updated

      {:status  201
       :headers {"Content-Type" "application/json"}
       :body    (json/write-str {:message "Usuário criado com sucesso!"
                                 :user    updated})}))


(add-user {:id 2 :nome "Teste" :idade 2})