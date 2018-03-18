;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname templateforvehiclestructs) (read-case-sensitive #t) (teachpacks ((lib "convert.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "convert.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 7.2.2 in the book:
;; " HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing "

;; This exercises introduces the reader to predicates and designing
;; programs for mixed types of data, with important changes in the data
;; definition process and function template.

;; Problem 7.2.2: The administrators of a metropolitan transportation
;; agencies manage fleets of vehicles. Develop structure and data
;; definitions for a colection of such vehicles. The colection should
;; include at least buses, limos, cars, and subways. Add at least two
;; attributes per class of vehicle. Then develop a template for functions
;; that consume vehicles.

(define size (length width))
;; size is a structure:
;;          (make-size l w)
;; where l and w are numbers.

(define-struct bus (size capacity driver))
(define-struct limo (size capacity))
(define-struct car (color capacity))
(define-struct subway (size capacity driver))
;; A vehicle is either:
;; 1. a bus structure:
;;             (make-bus s c d)
;;    where s is size structure and c is a numbers and d is a symbol.
;; 2. a limo structure:
;;             (make-limo s c)
;;    where s is a size structure and c is a number.
;; 3. a car structure:
;;             (make-car co c)
;;    where co is a symbol and c is a number.
;; 1. a subway structure:
;;             (make-subway s c d)
;;    where s is size structure and c is a numbers and d is a symbol.

;; Template
;; (define (fun-for-vehicle a-vehicle)
;;   (cond
;;     [(bus? a-vehicle)...(size-length (bus-s a-vehicle))...
;;                      ...(size-width (bus-s a-vehicle))...
;;                      ...(bus-c a-vehicle)...
;;                      ...(bus-d a-vehicle)...]
;;     [(limo? a-vehicle)...(size-length (limo-s a-vehicle))...
;;                       ...(size-width (limo-s a-vehicle))...
;;                       ...(limo-c a-vehicle)...]
;;     [(car? a-vehicle)...(car-co a-vehicle)...
;;                      ...(car-c a-vehicle)...]
;;     [(subway? a-vehicle)...(size-length (subway-s a-vehicle))...
;;                         ...(size-width (subway-s a-vehicle))...
;;                         ...(subway-c a-vehicle)...
;;                         ...(subway-d a-vehicle)...]))