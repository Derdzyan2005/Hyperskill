import math
import argparse

parser = argparse.ArgumentParser(description="Loan calculator")

parser.add_argument("--principal", type=float, help="the loan principal")
parser.add_argument("--payment", type=float, help="the monthly payment amount")
parser.add_argument("--periods", type=int, help="the number of months to repay the loan")
parser.add_argument("--interest", type=float, help="the loan interest")

args = parser.parse_args()

if args.interest is None:
    print("Interest rate must be provided.")
    exit()

i = args.interest / (12 * 100)

if args.principal is None:
    A = args.payment
    n = args.periods
    P = A / ((i * math.pow(1 + i, n)) / (math.pow(1 + i, n) - 1))
    print(f"Your loan principal = {round(P)}!")

elif args.payment is None:
    P = args.principal
    n = args.periods
    A = P * (i * math.pow(1 + i, n)) / (math.pow(1 + i, n) - 1)
    print(f"Your monthly payment = {math.ceil(A)}!")

elif args.periods is None:
    P = args.principal
    A = args.payment
    n = math.log(A / (A - i * P), 1 + i)
    n = math.ceil(n)
    
    years, months = divmod(n, 12)
    year_str = f"{years} year" + ("s" if years > 1 else "")
    month_str = f"{months} month" + ("s" if months > 1 else "")

    if years > 0 and months > 0:
        print(f"It will take {year_str} and {month_str} to repay this loan!")
    elif years > 0:
        print(f"It will take {year_str} to repay this loan!")
    else:
        print(f"It will take {month_str} to repay this loan!")

else:
    print("All parameters are provided. Nothing to calculate.")
