insert into user (id, name, location, age, money) values (1, 'Ljubo Mamic', 'Split', 24, 500.0)


insert into `transaction` (id, trans_date, type, money, user_id) values (1, '2020-02-15T17:00:00.000Z', 0, 300, 1)
insert into `transaction` (id, trans_date, type, money, user_id) values (2, '2020-02-16T17:00:00.000Z', 1, 40, 1)
insert into `transaction` (id, trans_date, type, money, user_id) values (3, '2020-02-17T17:00:00.000Z', 0, 77, 1)
insert into `transaction` (id, trans_date, type, money, user_id) values (4, '2020-02-18T17:00:00.000Z', 1, 112, 1)
insert into `transaction` (id, trans_date, type, money, user_id) values (5, '2020-02-19T17:00:00.000Z', 1, 77, 1)

insert into ticket (id, totalodd, possiblegain, transaction_id) values (1, 5.4, 325.30, 1)
insert into ticket (id, totalodd, possiblegain, transaction_id) values (2, 7.8, 335.10, 3)
insert into ticket (id, totalodd, possiblegain, transaction_id) values (3, 7.3, 567.60, 4)

insert into types (id, name, type1, type2, type3, type4, type5, type6) values (1, 'Football', '1', 'X', '2', '1X', 'X2', '12')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (2, 'Basketball', '1X', 'X2', '>=200p', '<200p', '1H10', '2H10')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (3, 'Tennis', '1', '2', '>23.5g', '<23.5g', '>=2.5h', '<2.5h')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (4, 'Waterpolo', '1', 'X', '2', '>15e', '<15e', 'Canceled')
insert into types (id, name, type1, type2, type3, type4, type5, type6) values (5, 'Boxing', '1', '2', 'Nockout', 'Full Match', 'Blood', 'No Blood')

insert into competitor (id, name, sport) values (1, 'FC Barcelona', 'Football')
insert into competitor (id, name, sport) values (2, 'Real Madrid C.F.', 'Football')
insert into competitor (id, name, sport) values (3, 'Cibona', 'Basketball')
insert into competitor (id, name, sport) values (4, 'Cedevita', 'Basketball')
insert into competitor (id, name, sport) values (5, 'Rafael Nadal', 'Tennis')
insert into competitor (id, name, sport) values (6, 'Roger Federer', 'Tennis')
insert into competitor (id, name, sport) values (7, 'Mladost', 'Waterpolo')
insert into competitor (id, name, sport) values (8, 'Jug', 'Waterpolo')
insert into competitor (id, name, sport) values (9, 'Mike Tyson', 'Boxing')
insert into competitor (id, name, sport) values (10, 'Muhammand Ali', 'Boxing')

insert into match (id, match_date, home_id, away_id, types_id) values (1, '2020-02-20T17:00:00.000Z', 1, 2, 1)
insert into match (id, match_date, home_id, away_id, types_id) values (2, '2020-02-21T17:00:00.000Z', 3, 4, 2)
insert into match (id, match_date, home_id, away_id, types_id) values (3, '2020-02-22T17:00:00.000Z', 5, 6, 3)
insert into match (id, match_date, home_id, away_id, types_id) values (4, '2020-02-23T17:00:00.000Z', 7, 8, 4)
insert into match (id, match_date, home_id, away_id, types_id) values (5, '2020-02-24T17:00:00.000Z', 9, 10, 5)

insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (1, 0, 1.8, 2.0, 2.8, 1.4, 1.8, 1.7, 1)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (2, 0, 2.2, 2.4, 5, 1.4, 2.5, 2.8, 2)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (3, 0, 2.0, 1.9, 1.4, 1.9, 1.8, 2.0, 3)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (4, 0, 2.2, 2.0, 1.9, 1.7, 2.4, 100.0, 4)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (5, 0, 2.1, 2.2, 3.0, 2.5, 1.05, 90, 5)
insert into odds (id, type, odd1, odd2, odd3, odd4, odd5, odd6, match_id) values (6, 1, 1.9, 2.1, 2.9, 1.5, 1.9, 1.8, 1)

insert into matches_per_ticket (ticket_id, odds_id) values (1,1)
insert into matches_per_ticket (ticket_id, odds_id) values (1,2)
insert into matches_per_ticket (ticket_id, odds_id) values (1,3)
insert into matches_per_ticket (ticket_id, odds_id) values (2,2)
insert into matches_per_ticket (ticket_id, odds_id) values (2,5)
insert into matches_per_ticket (ticket_id, odds_id) values (3,1)
insert into matches_per_ticket (ticket_id, odds_id) values (3,2)
insert into matches_per_ticket (ticket_id, odds_id) values (3,4)





