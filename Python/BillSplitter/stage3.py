import random

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

def split_bill(friends_dict, total_bill, lucky_person=None):
    if lucky_person:
        # If there's a lucky person, split bill among others
        split_amount = round(total_bill / (len(friends_dict) - 1), 2)
        return {name: split_amount if name != lucky_person else 0 for name in friends_dict}
    else:
        # Regular split
        split_amount = round(total_bill / len(friends_dict), 2)
        return {name: split_amount for name in friends_dict}

def process_lucky_feature(friends_dict):
    print('\nDo you want to use the "Who is lucky?" feature? Write Yes/No:')
    response = input()
    
    if response.lower() == 'yes':
        lucky_person = random.choice(list(friends_dict.keys()))
        print(f"\n{lucky_person} is the lucky one!")
        return lucky_person
    else:
        print("\nNo one is going to be lucky")
        return None

def main():
    # Get initial dictionary
    friends_dict = get_party_dictionary()
    if friends_dict is not None:
        # Get bill amount
        print("\nEnter the total bill value:")
        total_bill = float(input())
        
        # Process lucky feature
        lucky_person = process_lucky_feature(friends_dict)
        
        # Calculate final bill split
        final_dict = split_bill(friends_dict, total_bill, lucky_person)
        
        # Print final dictionary
        print()
        print(final_dict)

if __name__ == "__main__":
    main()
