insert into user (name, location, age, money) values ('Ljubo Mamic', 'Split', 24, 500.0)


insert into `transaction` (transactiondate, transactiontype, money, user_id) values ('2020-02-15T17:00:00.000Z', 0, 300, 1)
insert into `transaction` (transactiondate, transactiontype, money, user_id) values ('2020-02-16T17:00:00.000Z', 1, 40, 1)
insert into `transaction` (transactiondate, transactiontype, money, user_id) values ('2020-02-17T17:00:00.000Z', 0, 77, 1)
insert into `transaction` (transactiondate, transactiontype, money, user_id) values ('2020-02-18T17:00:00.000Z', 1, 12, 1)
insert into `transaction` (transactiondate, transactiontype, money, user_id) values ('2020-02-19T17:00:00.000Z', 1, 77, 1)

insert into ticket (totalodd, possiblegain, transaction_id) values (7.776, 311.4, 2)
insert into ticket (totalodd, possiblegain, transaction_id) values (7.25, 558.25, 5)
insert into ticket (totalodd, possiblegain, transaction_id) values (476.0, 5712.0, 4)

insert into types (id, name, type1, type2, type3, type4, type5, type6) values (1, 'Football', '1', 'X', '2', '1X', 'X2', '12')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (2, 'Basketball', '1X', 'X2', '>=200p', '<200p', '1H10', '2H10')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (3, 'Tennis', '1', '2', '>23.5g', '<23.5g', '>=2.5h', '<2.5h')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (4, 'Waterpolo', '1', 'X', '2', '>15e', '<15e', 'Canceled')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (5, 'Boxing', '1', '2', 'Nockout', 'Full Match', 'Blood', 'No Blood')

insert into match (id, matchdate, home, away, types_id) values (1, '2021-02-20T17:00:00.000Z', 'FC Barcelona', 'Real Madrid C.F.', 1)
insert into match (id, matchdate, home, away, types_id) values (2, '2021-02-21T17:00:00.000Z', 'Cibona', 'Cedevita', 2)
insert into match (id, matchdate, home, away, types_id) values (3, '2021-02-22T17:00:00.000Z', 'Rafael Nadal', 'Roger Federer', 3)
insert into match (id, matchdate, home, away, types_id) values (4, '2021-03-23T17:00:00.000Z', 'Mladost', 'Jug', 4)
insert into match (id, matchdate, home, away, types_id) values (5, '2021-05-24T17:00:00.000Z', 'Mike Tyson', 'Muhammad Ali', 5)

insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (1, 'Basic', 1.8, 2.0, 2.8, 1.4, 1.8, 1.7, 1)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (2, 'Basic', 2.2, 2.4, 5.0, 1.4, 2.5, 2.8, 2)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (3, 'Basic', 2.0, 1.9, 1.4, 1.9, 1.8, 2.0, 3)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (4, 'Basic', 2.2, 2.0, 1.9, 1.7, 2.4, 100.0, 4)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (5, 'Basic', 2.1, 2.2, 3.0, 2.5, 1.05, 90.0, 5)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (6, 'Special offer', 1.9, 2.1, 2.9, 1.5, 1.9, 1.8, 1)

insert into ticket_odds (ticket_id, odds_id, odd, type) values (1,1, 1.8, '1')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (1,2, 2.4, 'X2')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (1,3, 1.8, '>=2.5h')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (2,2, 2.5, '1H10')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (2,6, 2.9, '2')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (3,1, 1.7, '12')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (3,2, 2.8, '2H10')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (3,4, 100.0, 'Canceled')
