insert into user (name, location, age, money) values ('Ljubo Mamic', 'Split', 24, 500.0)

insert into ticket (totalodd, possiblegain, status) values (7.776, 311.04, 0)
insert into ticket (totalodd, possiblegain, status) values (7.25, 362.5, 0)
insert into ticket (totalodd, possiblegain, status) values (476.0, 23800.0, 0)

insert into `transaction` (transactiondate, transactiontype, money, taxes, manipulativespends, user_id, ticket_id) values ('2020-02-15T17:00:00.000Z', 0, 600, 0, 0, 1, null)
insert into `transaction` (transactiondate, transactiontype, money, taxes, manipulativespends, user_id, ticket_id) values ('2020-02-16T17:00:00.000Z', 1, 40, 0, 2, 1, 1)
insert into `transaction` (transactiondate, transactiontype, money, taxes, manipulativespends, user_id, ticket_id) values ('2020-02-17T17:00:00.000Z', 0, 40, 0, 0, 1, null)
insert into `transaction` (transactiondate, transactiontype, money, taxes, manipulativespends, user_id, ticket_id) values ('2020-02-18T17:00:00.000Z', 1, 50, 0, 2.5, 1, 2)
insert into `transaction` (transactiondate, transactiontype, money, taxes, manipulativespends, user_id, ticket_id) values ('2020-02-19T17:00:00.000Z', 1, 50, 0, 2.5, 1, 3)

insert into types (id, name, type1, type2, type3, type4, type5, type6) values (0, 'Football', '1', 'X', '2', '1X', 'X2', '12')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (1, 'Basketball', '1X', 'X2', '>=200p', '<200p', '1H10', '2H10')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (2, 'Tennis', '1', '2', '>23.5g', '<23.5g', '>=2.5h', '<2.5h')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (3, 'Waterpolo', '1', 'X', '2', '>15e', '<15e', 'Canceled')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (4, 'Boxing', '1', '2', 'Nockout', 'Full Match', 'Blood', 'No Blood')

insert into match (id, matchdate, home, away, types_id) values (0, '2025-02-20T17:00:00.000Z', 'FC Barcelona', 'Real Madrid C.F.', 0)
insert into match (id, matchdate, home, away, types_id) values (1, '2025-02-21T17:00:00.000Z', 'Cibona', 'Cedevita', 1)
insert into match (id, matchdate, home, away, types_id) values (2, '2025-02-22T17:00:00.000Z', 'Rafael Nadal', 'Roger Federer', 2)
insert into match (id, matchdate, home, away, types_id) values (3, '2025-03-23T17:00:00.000Z', 'Mladost', 'Jug', 3)
insert into match (id, matchdate, home, away, types_id) values (4, '2025-05-24T17:00:00.000Z', 'Mike Tyson', 'Muhammad Ali', 4)
insert into match (id, matchdate, home, away, types_id) values (5, '2025-02-27T16:00:00.000Z', 'Valencia', 'Real Betis', 0)
insert into match (id, matchdate, home, away, types_id) values (6, '2025-02-17T19:30:00.000Z', 'Atletico Madrid', 'Osasuna', 0)
insert into match (id, matchdate, home, away, types_id) values (7, '2025-02-22T16:00:00.000Z', 'Novak Djokovic', 'Marin Cilic', 2)
insert into match (id, matchdate, home, away, types_id) values (8, '2025-03-21T17:00:00.000Z', 'Chicago Bulls', 'Orlando Magic', 1)
insert into match (id, matchdate, home, away, types_id) values (9, '2025-03-21T17:30:00.000Z', 'Hajduk', 'Dinamo', 0)

insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (0, 'Basic', 1.8, 2.0, 2.8, 1.4, 1.8, 1.7, 0)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (1, 'Basic', 2.2, 2.4, 5.0, 1.4, 2.5, 2.8, 1)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (2, 'Basic', 2.0, 1.9, 1.4, 1.9, 1.8, 2.0, 2)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (3, 'Basic', 2.2, 2.0, 1.9, 1.7, 2.4, 100.0, 3)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (4, 'Basic', 2.1, 2.2, 3.0, 2.5, 1.05, 90.0, 4)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (5, 'Special offer', 1.9, 2.1, 2.9, 1.5, 1.9, 1.8, 0)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (6, 'Basic', 1.0, 2.0, 2.8, 1.4, 1.8, 1.7, 5)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (7, 'Basic', 1.05, 2.0, 2.8, 1.4, 1.8, 1.7, 6)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (8, 'Basic', 2.0, 1.9, 1.0, 1.9, 1.8, 2.0, 7)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (9, 'Special offer', 2.0, 1.9, 1.05, 1.9, 1.8, 2.0, 7)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (10, 'Basic', 2.2, 2.0, 1.0, 1.7, 2.4, 100.0, 8)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (11, 'Basic', 1.8, 1, 2.0, 1.6, 1.7, 2.5, 9)

insert into ticket_odds (ticket_id, odds_id, odd, type) values (1,0, 1.8, '1')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (1,1, 2.4, 'X2')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (1,2, 1.8, '>=2.5h')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (2,1, 2.5, '1H10')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (2,5, 2.9, '2')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (3,0, 1.7, '12')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (3,1, 2.8, '2H10')
insert into ticket_odds (ticket_id, odds_id, odd, type) values (3,3, 100.0, 'Canceled')
