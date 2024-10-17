DO $$
DECLARE
    productId INT;
    productHistoryId INT;
    productDetailsId INT;

BEGIN

    SELECT COALESCE(MAX(product_id), 0) + 1 INTO productId FROM product;
    SELECT COALESCE(MAX(product_history_id), 0) + 1 INTO productHistoryId FROM product_history;
    SELECT COALESCE(MAX(product_details_id), 0) + 1 INTO productDetailsId FROM product_details;

    -- CHARGE

    INSERT INTO product
        (product_id, name, weight, freight_value, estimated_delivery_date, street, number, city, state, postal_code)
    VALUES (productId, 'Microfone', 0.5, 10.0, '2024-10-25', 'Avenida Atlântica', '140', 'Rio de Janeiro', 'RJ', '09361190');

    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'POSTED', '09361180', '2024-10-20 10:00:00');

    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-10-20 10:00:00', '{"postalCode":"09361180","state":"BA","city":"Salvador","neighborhood":"Barra","street":"Avenida Sete de Setembro","latitude":-13.0153505,"longitude":-38.5279293}');



    productId :=productId + 1;
    INSERT INTO product
        (product_id, name, weight, freight_value, estimated_delivery_date, street, number, city, state, postal_code)
    VALUES (productId, 'Guarda Roupa', 60.0, 100.0, '2024-10-30', 'Rua Pernambuco', '650', 'Belo Horizonte', 'MG', '09361110');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'POSTED', '09361700', '2024-10-20 10:00:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-10-20 10:00:00', '{"postalCode":"09361700","state":"PR","city":"Curitiba","neighborhood":"Batel","street":"Avenida do Batel","latitude":-25.4417857,"longitude":-49.2895938}');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'IN_TRANSIT', '09360400', '2024-10-29 16:45:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-10-29 16:45:00', '{"postalCode":"09360400","state":"RS","city":"Porto Alegre","neighborhood":"Moinhos de Vento","street":"Rua Padre Chagas","latitude":-30.025033,"longitude":-51.2035559}');



    productId := productId + 1;
    INSERT INTO product
        (product_id, name, weight, freight_value, estimated_delivery_date, street, number, city, state, postal_code)
    VALUES (productId, 'Gabinete', 5.0, 12.0, '2024-11-01', 'Rua Domingos de Morais', '222', 'São Paulo', 'SP', '09361000');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'POSTED', '09360400', '2024-10-29 16:45:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-10-29 16:45:00', '{"postalCode":"09360400","state":"RS","city":"Porto Alegre","neighborhood":"Moinhos de Vento","street":"Rua Padre Chagas","latitude":-30.025033,"longitude":-51.2035559}');



    productId := productId + 1;
    INSERT INTO product
        (product_id, name, weight, freight_value, estimated_delivery_date, street, number, city, state, postal_code)
    VALUES (productId, 'Camiseta', 0.3, 20.0, '2024-11-05', 'Rua Padre Chagas', '854', 'Porto Alegre', 'RS', '09360400');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'POSTED', '09361000', '2024-11-01 12:00:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-11-01 12:00:00', '{"postalCode":"09361000","state":"SP","city":"São Paulo","neighborhood":"Vila Mariana","street":"Rua Domingos de Morais","latitude":-23.587416,"longitude":-46.641274}');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'IN_TRANSIT', '09361110', '2024-11-05 09:30:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-11-05 09:30:00', '{"postalCode":"09361110","state":"MG","city":"Belo Horizonte","neighborhood":"Savassi","street":"Rua Pernambuco","latitude":-19.9388494,"longitude":-43.9335402}');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'DELIVERED', '09360400', '2024-10-29 16:45:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-10-29 16:45:00', '{"postalCode":"09360400","state":"RS","city":"Porto Alegre","neighborhood":"Moinhos de Vento","street":"Rua Padre Chagas","latitude":-30.025033,"longitude":-51.2035559}');



    productId := productId + 1;
    INSERT INTO product
        (product_id, name, weight, freight_value, estimated_delivery_date, street, number, city, state, postal_code)
    VALUES (productId, 'Coberta', 1.0, 8.0, '2024-11-10', 'Avenida do Batel', '112', 'Curitiba', 'PR', '09361700');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'POSTED', '09361110', '2024-11-05 09:30:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-11-05 09:30:00', '{"postalCode":"09361110","state":"MG","city":"Belo Horizonte","neighborhood":"Savassi","street":"Rua Pernambuco","latitude":-19.9388494,"longitude":-43.9335402}');



    productId := productId + 1;
    INSERT INTO product
        (product_id, name, weight, freight_value, estimated_delivery_date, street, number, city, state, postal_code)
    VALUES (productId, 'Caneca', 0.4, 18.0, '2024-11-15', 'Avenida Sete de Setembro', '38', 'Salvador', 'BA', '09361180');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'POSTED', '09361190', '2024-11-12 11:00:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-11-12 11:00:00', '{"postalCode":"09361190","state":"RJ","city":"Rio de Janeiro","neighborhood":"Copacabana","street":"Avenida Atlântica","latitude":-22.9719645,"longitude":-43.1825535}');

    productHistoryId := productHistoryId + 1;
    INSERT INTO product_history
        (product_history_id, product_id, status, postal_code, update_timestamp)
    VALUES (productHistoryId, productId, 'IN_TRANSIT', '09361000', '2024-11-01 12:00:00');

    productDetailsId := productDetailsId + 1;
    INSERT INTO product_details
        (product_details_id, product_history_id, query_timestamp, api_response)
    VALUES (productDetailsId, productHistoryId, '2024-11-01 12:00:00', '{"postalCode":"09361000","state":"SP","city":"São Paulo","neighborhood":"Vila Mariana","street":"Rua Domingos de Morais","latitude":-23.587416,"longitude":-46.641274}');

END $$;