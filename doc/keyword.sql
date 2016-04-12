--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:22:55

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1494 (class 1259 OID 17458)
-- Dependencies: 6
-- Name: keyword; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE keyword (
    token character varying(32) NOT NULL
);


ALTER TABLE public.keyword OWNER TO erp;

--
-- TOC entry 1777 (class 2606 OID 17518)
-- Dependencies: 1494 1494
-- Name: idx_token; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY keyword
    ADD CONSTRAINT idx_token UNIQUE (token);


-- Completed on 2009-05-04 20:22:55

--
-- PostgreSQL database dump complete
--

