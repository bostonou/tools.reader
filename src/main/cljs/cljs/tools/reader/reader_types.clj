;;   Copyright (c) Nicola Mometto, Rich Hickey & contributors.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns ^{:doc "Protocols and default Reader types implementation"
      :author "Bronsa"}
  cljs.tools.reader.reader-types
  #_(:refer-clojure :exclude [read-line])
  #_(:require [cljs.tools.reader.impl.utils  :as utiles #_:refer
             #_[whitespace? newline? make-var]]))

(defmacro log-source
  "If reader is a SourceLoggingPushbackReader, execute body in a source
  logging context. Otherwise, execute body, returning the result."
  [reader & body]
  `(if (and (cljs.tools.reader.reader-types/source-logging-reader? ~reader)
            (not (cljs.tools.reader.impl.utils/whitespace? (cljs.tools.reader.impl.utils/peek-char ~reader))))
     (do ~@body)
     (do ~@body)))

;; (log-source* ~reader (^:once fn* [] ~@body))

(defmacro update! [what f]
  (list 'set! what (list f what)))