(ns users.update
  (:require [clojure.data.json :as json]
            [users.read :as read]
            [helpers.file-path :as file-path]))


(def user
  {:user-name "farelo de pao"
   :email     "farelo@farelo.com"
   :age       15})


(defn update-user
  [old-data-user type-field new-data-user]
  (cond
    (contains? old-data-user (keyword type-field))
    (-> old-data-user
        (dissoc (keyword type-field))
        (assoc (keyword type-field) new-data-user))
  :else (throw (Exception. "Dado nao encontrado!"))))


(update-user user "email" "eu.com")


(defn update-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        body (:json-params request)]
    (println "Print do body:" body)
    {:status 200
     :body   {:message (str "User " id " atualizado com: " body)}}))




















"_______________________________________________________________________"

;(def user
;  {:user-name "farelo de pao"
;   :email     "farelo@farelo.com"
;   :age       15})
;
;(assoc (dissoc user :email) :email "zequinha@teste")
;
;
;(defn change-email?
;  [data
;   new-email]
;  (if (contains? data :email)
;    (-> data
;        (dissoc :email)
;        (assoc :email (str new-email)))
;    (println "chave nâo encontrada")))
;
;(change-email? user "maia@maia.com")
;
;(defn put-user-data
;  [data type new-data]
;  (cond
;    (contains? data (keyword type))
;    (-> data
;        (dissoc (keyword type))
;        (assoc (keyword type) (str new-data)))
;
;    :else (throw (Exception. "Dado nao encontrado!"))))
;
;
;(put-user-data user "user-name" "Schmeller")
;(put-user-data user "email" "edu.schm@gmail.com")
;(put-user-data user "age" 26)

"_______________________________________________________________________"

#_(defn update-users-by-id [request]
    (let [id (get-in request [:path-params :id])
          users (read/get-users)
          user (first (filter #(= (str (:id %)) id) users))
          receba (:json-params request)]


      (println "AOO TAVAO: " (get-in request [:body]))


      (if user
        (let [updated-users (remove #(= (str (:id %)) id) users)]
          (spit file-path/file-path (json/write-str updated-users :pretty true))
          {:status  200
           :headers {"Content-Type" "application/json"}
           :body    {:message (str "Usuário com o id " receba " deletado com sucesso")}})
        {:status  404
         :headers {"Content-Type" "application/json"}
         :body    {:error (str "Usuário com o id " receba " não encontrado")}}
        )))