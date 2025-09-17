(ns api-crud-clojure.helpers.interceptors
  (:require [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http :as http]))

(def common-interceptors
  [(body-params/body-params)
   http/json-body])