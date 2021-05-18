# Gymscape :weight_lifting:

## Table of Contents
- [What is Gymscape](#what-is-gymscape)
- [Moscow Requirements](#moscow-requirements)
- [Figma Design](#figma-design)
- [Preview and some of the functions](#preview-and-some-of-the-functions)
  - [Login Page](#login-page)
  - [Exercises](#exercises)
  - [Workout](#workout)
  - [Calendar](#calendar)
- [Video Demonstration](#video-demonstration)

## What is Gymscape
Gymscape is an android app, which will track your progress in gym. It is based on gym experience, where each exercises from each trainings needed to be written in a notedpad, which is harder to lookup or easily lost. In Gymscape, all progress and previous weight used in excerises can be saved and searched in it, so it can be easily find out, how much weight was used, so user can push his/her progress.

## MoSCoW requirements

| Must Have | Implemented |
| --- | --- |
| Calendar with working days | :heavy_check_mark: |
| List of exercises | :heavy_check_mark: |
| Weight used and time trained each exercise | :heavy_check_mark: |
| Option to save the progress | :heavy_check_mark: |
| Option to change progress | :heavy_check_mark: |

| Should have | Implemented |
| --- | --- |
| Option to add, delete or edit exercise | :heavy_check_mark: |
| Which parts of body is used in specific exercise | :heavy_check_mark: |
| Picture how to train specific exercise correctly | :heavy_check_mark: |
| Login option | :heavy_check_mark: |

| Could have | Implemented |
| --- | --- |
| Planner for future exercise | :heavy_check_mark: |
| Graph of exercises to see progress | :x: |
| Option to add supersets (performing one exercise and immediate switch to another without break) | :x: |
| Edit your own profile | :x: |
| Achievements (ie. 1000 burned calories, 10 hours spend in gym etc.) | :x: |

| Won't have | Not Implemented |
| --- | --- |
| Option to look for profiles of another users | :heavy_check_mark: |
| Track progress of another users | :heavy_check_mark: |

## Figma Design
Before coding, design of the app was created. Design was created in Figma, so designing of actual app would be easier. Figma file was uploaded with actual code to github.
[Link on figma design](https://www.figma.com/file/yenDADhhSUkZ88sZEOyKb6/GymScape?node-id=0%3A1)

## Preview and some of the functions

### Login Page

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214632_GymScape.jpg" width="400" alt="Login Page" />

First thing users see after installing Gymscape is the login page. They can login with their account or register in new Activity, which will create new account and log them in. Also users can reset they password by clicking on Reset password and filling their email address.

### Exercises

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214646_GymScape.jpg" width="400" alt="Exercises" />

After successful login or register, user is taken to Exercise page, where exercises are categorized by muscles. Each category holds predefined exercises taken from webservices.

If user needs add exercise, it can be done by clicking on floating button in each category.

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214749_GymScape.jpg" width="400" alt="Add Exercise" />

For adding new exercise, user needs to name the exercise, describe how to do the exercise and take a picture how does the exercise look like.

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214811_GymScape.jpg" width="400" alt="Add to workout" />

Exercises can be then deleted or added to workout.
Only exercises, which user added by themselves can be deleted.

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214817_GymScape.jpg" width="400" alt="Delete Exercise/Add to workout" />

Exercises, which are predefined and taken from webservices, cannot be deleted.

### Workout

Each exercise can be added to workout. This can be done by clicking on exercise and then adding it to workout.

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214855_GymScape.jpg" width="400" alt="Add to workout" />

Exercise can be added to future or past workout by filling date. Sets and weight are optional and can be set later.

### Calendar

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214925_GymScape.jpg" width="400" alt="Calendar view" />

In calendar fragment, workouts can be seen by date. If there is no planned exercise, or no exercise was done, user is informed and can add workout to this date.

<img src="https://raw.githubusercontent.com/popalnicolas/Gymscape/master/screenshots/Screenshot_20210517-214939_GymScape.jpg" width="400" alt="Update workout" />

Also, if user wants to update his workout, they can do it by clicking on workout in the relevant date and change their values.

## Video Demonstration

[![image](https://img.youtube.com/vi/ws4Y_y9wklU/0.jpg)](https://www.youtube.com/watch?v=ws4Y_y9wklU)