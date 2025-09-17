(ns diplomat.http-client
  (:require [clojure.data.json :as data.json]
            [io.pedestal.http :as http]
            [diplomat.http-server :as diplomat.http-server]
            [io.pedestal.interceptor :as interceptor]
            [io.pedestal.http.body-params :as body-params]
            [cheshire.core :as json]))

(def users-db (atom []))

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