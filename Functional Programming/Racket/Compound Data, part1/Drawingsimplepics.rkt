;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-beginner-abbr-reader.ss" "lang")((modname Drawingsimplepics) (read-case-sensitive #t) (teachpacks ((lib "draw.rkt" "teachpack" "htdp"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "draw.rkt" "teachpack" "htdp")) #f)))
;; Author: Rúben Costa
;; rubencosta@live.com

;; Exercise 6.2.1/2/3/4 in the book "HOW TO DESIGN PROGRAMS - An
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


;; Code for a drawing of a traffic light with the red bulb turned on.
;; Necessary for the exercises to come.

;; Dimensions of traffic light
(define WIDTH 50)
(define HEIGHT 160)
(define BULB-RADIUS 20)
(define BULB-DISTANCE 10)

;; The positions of the bulbs
(define X-COOR (quotient WIDTH 2))
(define RED-Y-COOR (+ BULB-DISTANCE BULB-RADIUS))
(define YELLOW-Y-COOR (+ RED-Y-COOR BULB-DISTANCE (* 2 BULB-RADIUS)))
(define GREEN-Y-COOR (+ YELLOW-Y-COOR BULB-DISTANCE (* 2 BULB-RADIUS)))

;; Draw the traffic light with the red light on
(start WIDTH HEIGHT)
(draw-solid-disk (make-posn X-COOR RED-Y-COOR) BULB-RADIUS 'red)
(draw-circle (make-posn X-COOR YELLOW-Y-COOR) BULB-RADIUS 'yellow)
(draw-circle (make-posn X-COOR GREEN-Y-COOR) BULB-RADIUS 'green)

;; Exercise 6.2.2: Develop the function clear-bulb. It consumes a symbol
;; which denotes one of the possible colors: 'green, 'yellow, or 'red and
;; it produces true. Its effect is "to turn off" the matching bulb in the
;; traffic light. Specifically, it should clear the disk and display a
;; circle of the matching color instead.

;; clear-bulb: symbol -> boolean
;; to turn off a given bulb given a symbol by turning a disk into a circle
;; of the same color.
;; Example: (clear-bulb 'yellow) returns #true.
(define (clear-bulb acolor)
  (cond
    [(symbol=? acolor 'red) (and (clear-solid-disk
                                  (make-posn X-COOR RED-Y-COOR)
                                  BULB-RADIUS 'red)
                                 (draw-circle (make-posn X-COOR RED-Y-COOR)
                                              BULB-RADIUS 'red))]
    [(symbol=? acolor 'yellow) (and (clear-solid-disk
                                     (make-posn X-COOR YELLOW-Y-COOR)
                                     BULB-RADIUS 'yellow)
                                    (draw-circle
                                     (make-posn X-COOR YELLOW-Y-COOR)
                                     BULB-RADIUS 'yellow))]
    [(symbol=? acolor 'green) (and (clear-solid-disk
                                    (make-posn X-COOR GREEN-Y-COOR)
                                    BULB-RADIUS 'green)
                                   (draw-circle
                                    (make-posn X-COOR GREEN-Y-COOR)
                                    BULB-RADIUS 'green))]))

;; Tests
(check-expect (clear-bulb 'red) true)

;; Exercise 6.2.3: Develop a function draw-bulb. It consumes a symbol that
;; denotes one of the possible colors: 'green, 'yellow, 'red, and produces
;; true. Its effect is "to turn on" the matching bulb in the traffic light.

;; draw-bulb: symbol -> boolean
;; to turn on a bulb of a given color.
;; Example: (draw-bulb 'green) returns true and draws a green disk
(define (draw-bulb acolor)
  (cond
    [(symbol=? acolor 'red) (and (clear-circle
                                  (make-posn X-COOR RED-Y-COOR) BULB-RADIUS
                                  'red)
                                 (draw-solid-disk
                                  (make-posn X-COOR RED-Y-COOR) BULB-RADIUS
                                  'red))]
    [(symbol=? acolor 'yellow) (and (clear-circle
                                  (make-posn X-COOR YELLOW-Y-COOR)
                                  BULB-RADIUS
                                  'yellow)
                                 (draw-solid-disk
                                  (make-posn X-COOR YELLOW-Y-COOR)
                                  BULB-RADIUS
                                  'yellow))]
    [(symbol=? acolor 'green) (and (clear-circle
                                  (make-posn X-COOR GREEN-Y-COOR)
                                  BULB-RADIUS
                                  'green)
                                 (draw-solid-disk
                                  (make-posn X-COOR GREEN-Y-COOR)
                                  BULB-RADIUS
                                  'green))]))

;; Tests
(check-expect (draw-bulb 'green) true)

;; Exercise 6.2.4: Develop the function switch. It consumes two symbols,
;; each of which stands for a traffic light color, and produces true. Its
;; effects are to clear the bulb for the first color and then to draw the
;; second bulb.

;; switch: symbol symbol -> boolean
;; to clear the bulb with the first color and draw a disk with the second
;; color.
;; Example: (switch 'green 'yellow) turns off green and on yellow
(define (switch coloroff coloron)
  (cond
   [(and (symbol=? coloroff 'red) (symbol=? coloron 'yellow))
    (and (clear-bulb 'red) (draw-bulb 'yellow))]
   [(and (symbol=? coloroff 'red) (symbol=? coloron 'green))
    (and (clear-bulb 'red) (draw-bulb 'green))]
   [(and (symbol=? coloroff 'yellow) (symbol=? coloron 'red))
    (and (clear-bulb 'yellow) (draw-bulb 'red))]
   [(and (symbol=? coloroff 'yellow) (symbol=? coloron 'green))
    (and (clear-bulb 'yellow) (draw-bulb 'green))]
   [(and (symbol=? coloroff 'green) (symbol=? coloron 'yellow))
    (and (clear-bulb 'green) (draw-bulb 'yellow))]
   [else (and (clear-bulb 'green) (draw-bulb 'red))]))

;; Test
(check-expect (switch 'green 'yellow) true)
(check-expect (switch 'yellow 'red) true)
(check-expect (switch 'red 'green) true)