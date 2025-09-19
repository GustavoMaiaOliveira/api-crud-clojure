(ns users.update
  (:require [clojure.data.json :as json]
            [users.read :as read]
            [helpers.file-path :as file-path]))

#_(def user
  {:user-name "farelo de pao"
   :email     "farelo@farelo.com"
   :age       15})

#_(defn update-user
  [old-data-user type-field new-data-user]
  (cond
    (contains? old-data-user (keyword type-field))
    (-> old-data-user
        (dissoc (keyword type-field))
        (assoc (keyword type-field) new-data-user))
  :else (throw (Exception. "Dado nao encontrado!"))))

;(update-user user "email" "eu.com")

#_(defn update-users-by-id-1 [request]
  (let [id (get-in request [:path-params :id])
        body (:json-params request)]
    (println "Print do body:" body)
    {:status 200
     :body {:message (str "User " id " atualizado com: " body)}}))

#_(defn update-users-by-id [request
                          old-data-user
                          type-field]
  (let [id (get-in request [:path-params :id])
       body (:json-params request)]
    (cond
      (contains? old-data-user (keyword type-field))
      (-> old-data-user
          (dissoc (keyword type-field))
          (assoc (keyword type-field) body))
      :else (throw (Exception. "Dado nao encontrado!")))
  {:status 200
   :body {:message (str "User " id " atualizado com: " body)}}))

(defn update-users-by-id [request]
  (let [id (get-in request [:path-params :id])
        users (read/get-users)
        user (first (filter #(= (str (:id %)) id) users))
        delete-age (dissoc user :age)]

    ;(if (contains? user :age)
    ;  (-> user
    ;      (dissoc user :age))
    ;      )

      {:status  200
          :headers {"Content-Type" "application/json"}
          :body    {:message (str "Isso:" user)}}))



  ;(cond
  ;  (contains? :age user)
  ;  (-> user
  ;  ;(dissoc (:age user))
  ;      (println "batata" user)
  ;  {:status  200
  ;   :headers {"Content-Type" "application/json"}
  ;   :body    {:message (str "Isso:" user)}})
  ;  :else (throw (Exception. "Dado nao encontrado!")))


  ;)
;)















"_______________________________________________________________________"

#_(def user
  {:user-name "farelo de pao"
   :email     "farelo@farelo.com"
   :age       15})

#_(assoc (dissoc user :email) :email "zequinha@teste")

#_(defn change-email?
  [data
   new-email]
  (if (contains? data :email)
    (-> data
        (dissoc :email)
        (assoc :email (str new-email)))
    (println "chave nâo encontrada")))

#_(change-email? user "maia@maia.com")

#_(defn put-user-data
  [data type new-data]
  (cond
    (contains? data (keyword type))
    (-> data
        (dissoc (keyword type))
        (assoc (keyword type) (str new-data)))

    :else (throw (Exception. "Dado nao encontrado!"))))


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