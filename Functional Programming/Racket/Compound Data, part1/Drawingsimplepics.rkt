;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname Drawingsimplepics) (read-case-sensitive #t) (teachpacks ((lib "draw.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "draw.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 6.2.1/2/3/4/5 in the book "HOW TO DESIGN PROGRAMS - An
;; Introduction to Programming and Computing" by M.Felleisen, R.B.Findler,
;; M.Flatt, S.Krishnamurthi.

;; This exercise illustrates the use of data structures as inputs and
;; outputs, value extraction from records and use of global definitions
;; for defining constants

;; Problem 6.2.1: Evaluate the following functions in order:
(start 300 300) ;; opens the canvas for future drawing

(draw-solid-line (make-posn 1 1) (make-posn 5 5) 'red)
;; draws a line with starting/ending points and the color red

(draw-solid-rect (make-posn 20 10) 50 200 'blue)
;; draws a rectangle with upper left point width/height and color blue

(draw-circle (make-posn 200 10) 50 'red)
;; draws a circle with center point, radius and the color red

(draw-solid-disk (make-posn 200 10) 50 'green)
;; draws a disk with center point, radius and the color red

(stop) ;; closes the canvas