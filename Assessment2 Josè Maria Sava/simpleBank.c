/*
Question 4: Imperative Programming

Write a C program to simulate a simple banking system that allows a user to:
Deposit money
Withdraw money
Check account balance

Sava Josè Maria
*/

/* Include header files */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

/* Bank account structure */
typedef struct {
    char accountNumber[20]; // Number of the account
    float balance; // balance of the account
} Account; 

/* Function prototypes */
void initAccount(Account *bankAccount);
void depositMoney(Account *bankAccount, float amount);
void withdrawMoney(Account *bankAccount, float amount);
void checkAccountBalance(const Account *bankAccount);
int displayMenu();
float getPositiveAmount(const char *message);

/* main method */
int main(){

    /* Local vars */
    Account demoAccount;
    int menuChoice;

    /* Init account details */
    initAccount(&demoAccount);

    printf("\n === BANK SYSTEM === \n");

    while (true)
    {
        /* display User menù*/
        menuChoice = displayMenu();
        switch (menuChoice)
        {
        case 1:
            depositMoney(&demoAccount, getPositiveAmount("Enter amount to deposit: "));
            break;
        case 2:
            withdrawMoney(&demoAccount, getPositiveAmount("Enter amount to withdraw: "));
            break;
        case 3:
            checkAccountBalance(&demoAccount);
            break;
        case 4:
            printf("Thank you for using our bank! \n");
            return 0; 
            break;
        default:
            break;
        }
    }
    return 0; 
}

/*
* Function name     :   initAccount
* Arguments         :   Account *bankAccount = pointer to the account structure to initialize
* Return value/s    :   void         
* Remarks           :   Prompts the user for the account holder's name and sets the balance to 0.
*/
void initAccount(Account *bankAccount){
    printf("Enter account number: ");
    fgets(bankAccount->accountNumber, sizeof(bankAccount->accountNumber), stdin);
    /* Remove newline */
    size_t len = strlen(bankAccount->accountNumber);
    if (len > 0 && bankAccount->accountNumber[len-1] == '\n')
    {
        bankAccount->accountNumber[len - 1] = '\0';
    }
    bankAccount->balance = 0.0f; 
}

/*
* Function name     :   deposit
* Arguments         :   Account *bankAccount = pointer to the account structure
*                       float amount         = amount to deposit
* Return value/s    :   
* Remarks           :   Adds the deposit amount to the account balance and prints the result.
*/
void depositMoney(Account *bankAccount, float amount){

    bankAccount->balance += amount;
    printf("Successfully deposited $%.2f \n", amount);
    printf("New balance: $%.2f\n", bankAccount->balance); 
}

/*
* Function name     :   withdraw
* Arguments         :   Account *bankAccount  = pointer to the account structure
*                       float amount          = amount to withdraw
* Return value/s    :   
* Remarks           :   Subtracts the withdrawal amount from the balance if sufficient funds exist.
*/
void withdrawMoney(Account *bankAccount, float amount){

    if (amount > bankAccount->balance)
    {
        printf("Insufficient balance! Withdrawal not possible! \n");
    }
    else
    {
        bankAccount->balance -= amount;
        printf("Withdrawal successful! \n");
        printf("New balance: $%.2f\n", bankAccount->balance);
    }
}

/*
* Function name     :   checkBalance
* Arguments         :   const Account *bankAccount = pointer to the account structure (read-only)
* Return value/s    :   
* Remarks           :   Prints the account holder's name and current balance.
*/
void checkAccountBalance(const Account *bankAccount){
    printf("---- Balance ----\n");
    printf("Account number : %s\n", bankAccount->accountNumber);
    printf("Current balance : $%.2f\n", bankAccount->balance);
}

/*
* Function name     :   displayMenu
* Arguments         :   void
* Return value/s    :   int  = user's validated menu choice (1-4)
* Remarks           :   Displays the menu, validates input, and returns the user's choice.
*/
int displayMenu(){
    int choice;
    char buffer[100];
    while (true)
    {
        printf("1. Deposit Money \n"
               "2. Withdraw Money \n"
               "3. Check Account Balance \n"
               "4. Exit \n"
               "Enter your choice (1-4): \n"
        );
        if (fgets(buffer, sizeof(buffer), stdin)) {
            if (sscanf(buffer, "%d", &choice) == 1 && choice >= 1 && choice <= 4) {
                return choice;
            }
        }
        printf("Invalid input. Please enter a number between 1 and 4.\n");
    }
}

/*
* Function name     :   getPositiveAmount
* Arguments         :   const char *message = prompt message for the user
* Return value/s    :   float               = validated positive amount entered by the user
* Remarks           :   Prompts the user for a positive float and validates the input.
*/
float getPositiveAmount(const char *message) {
    float amount;
    char buffer[100];
    while (1) {
        printf("%s", message);
        if (fgets(buffer, sizeof(buffer), stdin)) {
            if (sscanf(buffer, "%f", &amount) == 1 && amount > 0) {
                return amount;
            }
        }
        printf("Invalid amount. Please enter a positive number.\n");
    }
}

