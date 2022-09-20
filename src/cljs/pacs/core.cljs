(ns pacs.core
  (:require ["@inertiajs/inertia" :refer [Inertia]]
            ["@inertiajs/inertia-react" :refer [createInertiaApp]]
            ["@inertiajs/progress" :refer [InertiaProgress]]
            [applied-science.js-interop :as j]
            [pacs.pages.welcome :as welcome]
            [pacs.shared.layout :refer [layout]]
            [reagent.core :as r]
            [reagent.dom :as d]))

(.init InertiaProgress)

(def pages {"Welcome/Index" welcome/index})

(defn init! []
  (createInertiaApp
   #js {:resolve (fn [name]
                   (let [^js comp (r/reactify-component (get pages name))]
                     (when-not (= name "Auth/Login")
                       (set! (.-layout comp) (fn [page] (r/as-element [layout page]))))
                     comp))
        :title (fn [title] (str title " | PACS"))
        :setup (j/fn [^:js {:keys [el App props]}]
                 (d/render (r/as-element [:f> App props]) el))}))

(defn ^:dev/after-load reload []
  (.reload Inertia))
