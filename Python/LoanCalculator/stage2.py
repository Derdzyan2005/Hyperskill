import math

loan_principal = float(input("Enter the loan principal:\n"))

calculation_type = input("What do you want to calculate?\ntype 'm' for number of monthly payments,\ntype 'p' for the monthly payment:\n")

if calculation_type == 'm':
    monthly_payment = float(input("Enter the monthly payment:\n"))
    months = loan_principal / monthly_payment
    print(f"\nIt will take {math.ceil(months)} months to repay the loan")

elif calculation_type == 'p':
    months = int(input("Enter the number of months:\n"))
    monthly_payment = math.ceil(loan_principal / months)
    last_payment = loan_principal - (monthly_payment * (months - 1))
    if loan_principal % months == 0:
        print(f"\nYour monthly payment = {monthly_payment}")
    else:
        print(f"\nYour monthly payment = {monthly_payment} and the last payment = {last_payment}")

else:
    print("Invalid option. Please enter 'm' or 'p'.")
