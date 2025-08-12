(ns api-crud-clojure.core
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

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
   :body {:username "Gustavo Maia"
          :email "gustavo.maia@mmti.io"
          :country "Brazil"
          :city "SJP - PR"}})

(def routes
  (route/expand-routes
    #{["/hello" :get funcao-hello :route-name :hello-world]
      ["/users" :post get-users :route-name :users]}))

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