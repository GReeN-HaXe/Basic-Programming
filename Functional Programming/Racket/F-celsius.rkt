;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname F-celsius) (read-case-sensitive #t) (teachpacks ((lib "convert.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "convert.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 2.2.1 in the book:
;; "HOW TO DESIGN PROGRAMS - An Introduction to Programming and Computing"
;; by M.Felleisen, R.B.Findler, M.Flatt, S.Krishnamurthi

;; Demonstrates the use of basic functions and program design
;; used in the book to create programs.

;; Fahrenheit->Celsius: number->number
;; A function that takes a temperature in Fahrenheit
;; and converts it to a temperature in Celsius.
;; Example: (= (Fahrenheit->Celsius 212) 99)
(define (Fahrenheit->Celsius temp)
;; Mathematical formula to convert Fahrenheit to Celsius
  (* (- temp 32) k))
;; A constant needed in the conversion formula
(define k 0.55)

;; Tests
(check-within (Fahrenheit->Celsius 32) 0 0.01)
(check-within (Fahrenheit->Celsius 0) -17.6 0.01)
(check-within (Fahrenheit->Celsius 100) 37.4 0.01)
(check-within (Fahrenheit->Celsius 212) 99 0.01)

;; function in the convert.rkt teach pack that allows a user
;; to test the function in a graphical user interface
(convert-gui Fahrenheit->Celsius)

;; function in the convert.rkt teach pack that allows a user to
;; test the function with an interactions window
;;(convert-repl Fahrenheit->Celsius)

;; function in the convert.rkt teach pack that allows a user to
;; read values from a file and output the results of the function
;; in another file in "C:\Users\username\" folder
;;(convert-file "input.dat" Fahrenheit->Celsius "output.dat") 