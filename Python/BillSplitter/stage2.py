def get_party_dictionary():
    # Get the number of friends joining
    print("Enter the number of friends joining (including you):")
    num_friends = int(input())
    
    # Check for invalid input (zero or negative)
    if num_friends <= 0:
        print("\nNo one is joining for the party")
        return None
    
    # Initialize empty dictionary to store names
    friends_dict = {}
    
    # Get names and add to dictionary
    print("\nEnter the name of every friend (including you), each on a new line:")
    for _ in range(num_friends):
        name = input()
        friends_dict[name] = 0
    
    return friends_dict

def split_bill(friends_dict):
    print("\nEnter the total bill value:")
    total_bill = float(input())
    
    # Calculate split amount rounded to 2 decimal places
    split_amount = round(total_bill / len(friends_dict), 2)
    
    # Update dictionary with split amounts
    return {name: split_amount for name in friends_dict}

def main():
    friends_dict = get_party_dictionary()
    if friends_dict is not None:
        friends_dict = split_bill(friends_dict)
        print()  # Empty line for formatting
        print(friends_dict)

if __name__ == "__main__":
    main()
