//
//  ViewController.m
//  Zadanie 4
//
//  Created by Dominik on 21/10/2021.
//  Copyright © 2021 Dominik. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self becomeFirstResponder];
    // Do any additional setup after loading the view.
}

-(void)motionEnded:(UIEventSubtype)motion withEvent:(UIEvent *)event
{
    if(motion == UIEventSubtypeMotionShake)
        [self showShakeDetectedAlert];
}

-(BOOL)canBecomeFirstResponder {
    return YES;
}

-(IBAction)showShakeDetectedAlert
{
    UIAlertController* alertController = [UIAlertController alertControllerWithTitle:@"Shake gesture detected" message:@"Do you want to change the background color?" preferredStyle:UIAlertControllerStyleAlert];

    UIAlertAction* yesButton = [UIAlertAction actionWithTitle:@"Yes" style:UIAlertActionStyleDefault handler:^(UIAlertAction* action){
        UIColor* bgColor = [[UIColor alloc]initWithRed:(rand()%11)/10.0 green:(rand()%11)/10.0 blue:(rand()%11)/10.0 alpha:1.0];
        self.view.backgroundColor = bgColor;
    }];
    
    UIAlertAction* noButton = [UIAlertAction actionWithTitle:@"No" style:UIAlertActionStyleDefault handler:^(UIAlertAction* action){
        NSLog(@"Shake motion detected");
    }];
    
    [alertController addAction:yesButton];
    [alertController addAction:noButton];
    [self presentViewController:alertController animated:YES completion:nil];
}

- (void)tapGesture:(UITapGestureRecognizer *)sender
{
    _gestureLabel.text = @"Tap detected";
}

- (void)pinchGesture:(UIPinchGestureRecognizer *)sender
{
    _gestureLabel.text = @"Pinch detected";
}

- (void)swipeGesture:(UISwipeGestureRecognizer *)sender
{
    _gestureLabel.text = @"Swipe detected";
}

- (void)longPressGesture:(UILongPressGestureRecognizer *)sender
{
    _gestureLabel.text = @"Long press detected";
}

@end
