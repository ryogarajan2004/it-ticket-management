# Ticket Management Console App (Training Project)

## Purpose

This repository provides a base skeleton for a console-based
IT Ticket Management System.

## Rules

- Do NOT modify model fields or enums
- Do NOT add file I/O outside DataFileManager
- Implement logic only in new classes or app.Main menu
- Follow role-based access strictly

## Roles

- USER: raise & confirm tickets
- AGENT: resolve & manage tickets
- ADMIN: view all tickets

## Persistence

Optional `.dat` file-based persistence is handled centrally.
If file is missing or corrupted, system starts with empty data.

## Scope

Training-only. No JDBC. No frameworks.

Sample test data is available under /testcases/sample-data.txt
for reference and manual testing.
