(ns api-crud-clojure.diplomat.http-client
  (:require [io.pedestal.http :as http]
            [api-crud-clojure.diplomat.http-server :as diplomat.http-server]))

(def service-map
  {:env :prod
   ::http/type :jetty
   :io.pedestal.http/host "localhost"
   :io.pedestal.http/port 9999
   :io.pedestal.http/routes diplomat.http-server/routes})

(defonce server (atom nil))

(defn start []
  (reset! server
          (http/start (http/create-server service-map)))
  (println "Servidor iniciado em http://localhost:9999"))

(defn stop []
  (when @server
    (http/stop @server)
    (reset! server nil)
    (println "Servidor parado")))

(defn -main [& args]
  (start))