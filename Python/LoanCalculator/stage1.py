loan_principal = float(input("Enter the loan principal:\n"))
monthly_payment = float(input("Enter the monthly payment:\n"))
months = loan_principal / monthly_payment
print(f"\nIt will take {int(months)} months to repay the loan")
