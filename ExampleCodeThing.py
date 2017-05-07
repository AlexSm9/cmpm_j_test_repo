# Alexander Smirnov
# asmirno1@ucsc.edu
# programming assignment 3
# Plays a guessing game with the user, asking them to find a number between 1 and 10 and
#   giving feedback if the guess is too high or too low.

import random


def check_guess(numtoguess, numguessed):
    """Function to check guess against random number, print appropriate result and return True if guess matches."""
    if numtoguess == numguessed:
        print("You win!\n")
        return True
    if numguessed < numtoguess:
        print("Your guess is too low.\n")
        return False
    if numguessed > numtoguess:
        print("Your guess is too high.\n")
        return False


def get_input(ordinalstring):
    """Simple function that queries input with ordinal string as argument and returns input."""
    return int(input("Enter your " + ordinalstring + " guess: "))

# Variable Initialization
numGuesses = 7
guess = -1
minV = 1
maxV = 100
randNum = random.randint(minV, maxV)
print("\nI'm thinking of an integer in the range " + str(minV) + " to " + str(maxV) + ". You have three guesses.\n")

# Main Program Loop
for x in range(numGuesses):
    if x == 0:
        guess = get_input("first")
        if check_guess(randNum, guess) is True:
            break
    elif x == 1:
        guess = get_input("second")
        if check_guess(randNum, guess) is True:
            break
    elif x == 2:
        guess = get_input("third")
        if check_guess(randNum, guess) is True:
            break
    else:
        guess = get_input("next")
        if check_guess(randNum, guess) is True:
            break

# Print loss message with the unguessed number if number has not been guessed in enough tries.
if guess != randNum:
    print("You lose. The number was " + str(randNum) + ".\n")

