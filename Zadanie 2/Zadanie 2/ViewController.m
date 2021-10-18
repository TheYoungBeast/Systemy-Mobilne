	//
//  ViewController.m
//  Zadanie 2
//
//  Created by Dominik on 17/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

- (IBAction)enter
{
    NSString *yourName = self.inputField.text;
    NSString *myName = @"Dominik";
    NSString *message;
    
    if ([yourName length] == 0)
        yourName = @"World";
    
    if ([yourName isEqualToString:myName])
        message = [NSString stringWithFormat:@"Hello %@! We have the same name :)", yourName];
    else
        message = [NSString stringWithFormat:@"Hello %@!", yourName];
    
    self.messageLabel.text = message;
}

- (void) prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    
    if([segue.identifier isEqualToString:@"SendSurnameSegue"])
    {
        SecondViewController* controller = (SecondViewController*) segue.destinationViewController;
        controller.surname = self.surnameField.text;
        controller.delegate = self;
    }
}

- (void) addItemViewController:(SecondViewController *)controller didFinishEnteringItem:(NSString *)item
{
    NSLog(@"This was returned from SecondViewController %@", item);
    self.surnameField.text = item;
}

@end
