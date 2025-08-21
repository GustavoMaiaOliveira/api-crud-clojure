(ns api-crud-clojure.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [clojure.data.json :as json]
<<<<<<< HEAD
            [clojure.java.io :as io]))

def struct

(def file-path "usuarios.json")

;Crie uma struct passando pra ela todos os dados que voce quer passar para adicionar um usuario novo (EX: nome, email, senha, nacionalidade)

;Crie uma funcao para adicionar usuarios recebendo esses dados da struct no body da requisicao.

;Pesquise nas documentacoes do pedestal e do clojure como voce pode criar struct e atribuir valor ao body do json





(defn ler-usuarios []
  (if (.exists (io/file file-path))
    (with-open [r (io/reader file-path)]
      (json/read r :key-fn keyword))
    []))

(defn add-users []
  )

#_(defn salvar-usuarios [usuarios]
  (with-open [w (io/writer file-path)]
    (json/write usuarios w)))

#_(defn adicionar-usuario [novo-usuario]
  (let [usuarios (ler-usuarios)
        usuarios-atualizados (conj usuarios novo-usuario)]
    (salvar-usuarios usuarios-atualizados)))

(defn funcao-hello
  [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello World"})

(defn get-users
  [request]
  (println request)
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (ler-usuarios)
   })

(defn add-users [new-user]
  )

(def routes
  (route/expand-routes
    #{["/hello" :get funcao-hello :route-name :hello-world]
      ["/users" :get get-users :route-name :users]
=======
            [api-crud-clojure.hello :as hello]
            [users.read :as read]))

(def routes
  (route/expand-routes
    #{["/hello"
       :get hello/funcao-hello
       :route-name :hello-world]

      ["/users"
       :get read/read-users
       :route-name :users]

>>>>>>> 154a526 (read function/rout working)
      }))

(def service-map
  {::http/routes routes
   ::http/type :jetty
   ::http/port 9999
   ::http/join? false})

(defonce server (atom nil))

(defn start []
  (reset! server
          (http/start (http/create-server service-map)))
  (println "Servidor iniciado em http://localhost:9999/hello"))

(defn stop []
  (when @server
    (http/stop @server)
    (reset! server nil)
    (println "Servidor parado")))

(defn -main [& args]
  (start))

;(defn get-users
;  [request]
;  (println request)
;  {:status 200
;   :headers {"Content-Type" "application/json"}
;   :body {:username "Gustavo Maia"
;          :email "gustavo.maia@mmti.io"
;          :country "Brazil"
;          :city "SJP - PR"}})