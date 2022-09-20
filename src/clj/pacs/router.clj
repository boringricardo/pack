(ns pacs.router
  (:require [inertia.middleware :as inertia]
            [pacs.handlers.welcome :as welcome]
            [pacs.middleware.inertia :refer [wrap-inertia-share]]
            [pacs.templates.error :refer [not-found]]
            [pacs.templates.app :refer [template]]
            [reitit.coercion.schema :as schema-coercion]
            [reitit.dev.pretty :as pretty]
            [reitit.ring :as ring]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.parameters :as params]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.session.cookie :refer [cookie-store]]
            [schema.core :as s]))

(def asset-version "1")

(def cookie-store-secret (byte-array 16))

(defn config [db]
  {:conflicts nil
   :exception pretty/exception
   :data {:coercion schema-coercion/coercion
          :middleware [params/parameters-middleware
                       rrc/coerce-exceptions-middleware
                       rrc/coerce-request-middleware
                       rrc/coerce-response-middleware
                       wrap-keyword-params
                       [wrap-session {:store (cookie-store {:key cookie-store-secret})}]
                       wrap-flash
                       ;; insert auth middleware here
                       [wrap-inertia-share db]
                       [inertia/wrap-inertia template asset-version]]}})

(defn routes [db]
  (ring/ring-handler
   (ring/router
    ["/" {:get welcome/index}]
    (config db))
   (ring/routes
    (ring/create-file-handler {:path "/"})
    (ring/create-default-handler
     {:not-found (constantly {:status 404 :body not-found})}))))
