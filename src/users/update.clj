(ns users.update
  (:require [clojure.data.json :as json]
            [cheshire.core :as json2]
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
       :body   {:message (str "User " id " atualizado com: " body)}}))

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
       :body   {:message (str "User " id " atualizado com: " body)}}))

;(def request-mock
;  {:json-params {:age 33}, :protocol HTTP/1 .1, :async-supported? true, :remote-addr 127.0.0.1, :servlet-response #object[org.eclipse.jetty.server.Response 0x48b8c1f7 HTTP/1 .1 200 ], :servlet #object[io.pedestal.http.servlet.FnServlet 0x13f0c3ef io.pedestal.http.servlet.FnServlet @13f0c3ef], :headers {accept */*, user-agent PostmanRuntime/7 .47.1, connection keep-alive, postman-token 943accee-c1c1-4ece-9587-00bc179f830d, host localhost:9999, accept-encoding gzip, deflate, br, content-length 30, content-type application/json}, :server-port 9999, :servlet-request #object[org.eclipse.jetty.server.Request 0x1c926e2a Request (PUT //localhost:9999/users/19) @1c926e2a], :content-length 30, :content-type application/json, :path-info /users/19, :character-encoding UTF-8, :url-for #object[clojure.lang.Delay 0x46183559 {:status :pending, :val nil}], :uri /users/19, :server-name localhost, :query-string nil, :path-params {:id 19}, :body #object[org.eclipse.jetty.server.HttpInputOverHTTP 0x46cc7e93 HttpInputOverHTTP @46cc7e93 [c=30, q=0, [0] =null, s=STREAM]], :scheme :http, :request-method :put, :context-path} )

(defn get-data-as-json []
  (json/read-json (slurp "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users-backup.json")))

(defn get-data-by-id [id]
  (println "get-data-by-id: " id)
  (first (filter #(= (num (:id %)) id)
                 (json/read-json (slurp "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users-backup.json")))))

(get-data-by-id 19)



(defn update-users-by-id
  [request]
  (let [id (get-in request [:path-params :id])
        body (:json-params request)
        user (get-data-by-id (read-string id))
        new-age (:age body)
        old-age (:age (get-data-by-id id))]

    (println "tipo do id do rapaz:" (type id))
    (println "tipo do numero do rapaz:" (type 19))
    (println "tipo da string rapaz:" (type "id"))
    (println "USER sem assoc" (get-data-by-id (read-string id)))

    (if (= (:id user) id)
      (-> user
          (dissoc :age)
          (assoc :age new-age)
          println)

      (print "Voce me chamou do que????"))

    {:status  200
     :headers {"Content-Type" "application/json"}
     :body    {:message (str "Retorno: " (:age user))}}))





;(receba)

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





#_(first (filter #(= (:id %) 2005)
                 (json/read-json (slurp "/home/gustavo_maia/IdeaProjects/api-crud-clojure/users-backup.json"))))










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