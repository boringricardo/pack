(ns pack.handlers.welcome
  (:require [inertia.middleware :as inertia]))

(defn index [_]
  (inertia/render "Welcome/Index"))
