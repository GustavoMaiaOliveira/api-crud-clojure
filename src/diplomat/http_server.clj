(ns diplomat.http-server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [helpers.api-version :as api-version]
            [users.create :as users.create]
            [users.delete :as users.delete]
            [users.read :as users.read]
            [users.update :as users.update]
            ))

(def common-interceptors
  [(body-params/body-params)
   http/json-body])

(def routes
  (route/expand-routes
    #{["/api-version"
       :get api-version/api-version
       :route-name :api-version]
      ["/users"
       :post users.create/create-new-user
       :route-name :create-users]
      ["/users"
       :get users.read/read-users
       :route-name :get-all-users]
      ["/users/:id"
       :get users.read/read-users-by-id
       :route-name :read-users-by-id]
      ["/users/:id"
       :put (conj common-interceptors users.update/update-users-by-id)
       :route-name :update-users-by-id]
      ["/users/:id"
       :delete users.delete/delete-users-by-id
       :route-name :delete-users]}))