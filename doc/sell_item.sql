--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:23:38

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1501 (class 1259 OID 17480)
-- Dependencies: 1776 6
-- Name: sell_item; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE sell_item (
    id integer NOT NULL,
    sell_id integer NOT NULL,
    ware_id integer NOT NULL,
    number integer NOT NULL,
    price real DEFAULT 0 NOT NULL
);


ALTER TABLE public.sell_item OWNER TO erp;

--
-- TOC entry 1502 (class 1259 OID 17484)
-- Dependencies: 6 1501
-- Name: sell_item_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE sell_item_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sell_item_id_seq OWNER TO erp;

--
-- TOC entry 1783 (class 0 OID 0)
-- Dependencies: 1502
-- Name: sell_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE sell_item_id_seq OWNED BY sell_item.id;


--
-- TOC entry 1777 (class 2604 OID 17512)
-- Dependencies: 1502 1501
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE sell_item ALTER COLUMN id SET DEFAULT nextval('sell_item_id_seq'::regclass);


--
-- TOC entry 1780 (class 2606 OID 17524)
-- Dependencies: 1501 1501
-- Name: sell_lst_p_code; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY sell_item
    ADD CONSTRAINT sell_lst_p_code PRIMARY KEY (id);


--
-- TOC entry 1778 (class 1259 OID 17537)
-- Dependencies: 1501
-- Name: sell_item_sellId; Type: INDEX; Schema: public; Owner: erp; Tablespace: 
--

CREATE INDEX "sell_item_sellId" ON sell_item USING btree (sell_id);


-- Completed on 2009-05-04 20:23:38

--
-- PostgreSQL database dump complete
--

