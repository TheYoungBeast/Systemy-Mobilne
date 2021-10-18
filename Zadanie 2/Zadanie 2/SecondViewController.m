//
//  SecondViewController.m
//  Zadanie 2
//
//  Created by Dominik on 17/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import "SecondViewController.h"

@interface SecondViewController ()

@end

@implementation SecondViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.modifiedSurnameInputField.text = self.surname;
}

- (IBAction)goBack
{
    NSString* itemToPassBack = self.modifiedSurnameInputField.text;
    [self.delegate addItemViewController:self didFinishEnteringItem:itemToPassBack];
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
