CREATE PROCEDURE `existeixNomEntrenador`
(IN `p_nom` VARCHAR(50), OUT `p_existe` BOOLEAN) 
NOT DETERMINISTIC CONTAINS SQL SQL SECURITY DEFINER 
BEGIN 
	DECLARE v_existe BOOLEAN DEFAULT FALSE; 
	DECLARE v_nom VARCHAR(50); 
	DECLARE exit_loop BOOLEAN DEFAULT FALSE; 
	DECLARE cursor1 CURSOR FOR SELECT nom FROM entrenador; 
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET exit_loop = TRUE; 
	SET p_existe = FALSE; 
	OPEN cursor1; 
	entrenador_loop: LOOP 
		FETCH cursor1 INTO v_nom; 
		IF exit_loop THEN 
			LEAVE entrenador_loop; 
		END IF; 
		IF v_nom = p_nom THEN 
			SET p_existe = TRUE; 
			LEAVE entrenador_loop; 
		END IF; 
	END LOOP entrenador_loop; 
	CLOSE cursor1; 
END