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

# Main program execution
def main():
    result = get_party_dictionary()
    if result is not None:
        print()  # Empty line for formatting
        print(result)

if __name__ == "__main__":
    main()
