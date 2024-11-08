print("""Earned amount:
Bubblegum: $202
Toffee: $118
Ice cream: $2250
Milk chocolate: $1680
Doughnut: $1075
Pancake: $80""")

total_sum = 202 + 118 + 2250 + 1680 + 1075 + 80

print("Income: $" + str(total_sum))

staff_expenses = input("Staff expenses: ")
other_expenses = input("Other expenses: ")
total_expenses = int(staff_expenses) + int(other_expenses)

net_income = total_sum - total_expenses
print("Net income: $" + str(net_income))
