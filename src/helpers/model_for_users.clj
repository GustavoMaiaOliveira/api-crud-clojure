(ns helpers.model-for-users
  (:require [schema.core :as s]))

(s/defschema skeleton-user-model
  {:id s/Num
   :name s/Str
   :age s/Num})

(s/defschema schema-keyword-age
  {:age s/Num})