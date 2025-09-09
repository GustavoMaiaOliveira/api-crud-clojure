(ns api-crud-clojure.diplomat.http-in.users
  (:require [api-crud-clojure.controller.users :as controllers.users]))


;TODO - 1 criar endpoint post - Receber dados - entender como salvar esses dados em umjson ja existente - retornar info do user cirado



(defn get-all-users
  [request]
  (controllers.users/get-users request))

(def users-db (atom []))

(defn create-users
  [request]
  (let [user-data (get-in request [:json-params])] ;; pega o corpo JSON
    (swap! users-db conj user-data)                ;; salva no "banco"
    {:status 201
     :body {:message "Usuário criado com sucesso!"
            :user request}}))

