(ns user
  (:require [integrant.repl :as ig-repl]
            [integrant.repl.state :as state]
            [pacs.core :as system]))

(ig-repl/set-prep!
 (fn [] system/config))

(def go ig-repl/go)
(def halt ig-repl/halt)
(def reset ig-repl/reset)
(def reset-all ig-repl/reset-all)

(def app (-> state/system :pacs/app))
(def db (-> state/system :database.sql/connection))

(comment
  ;; define REPL methods here for easy access
  (go))